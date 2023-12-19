import { Component, ViewChild, ElementRef } from '@angular/core';
import SignaturePad from 'signature_pad';
import { MatDialog ,MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
@Component({
  selector: 'app-signature-pad',
  templateUrl: './signature-pad.component.html',
  styleUrls: ['./signature-pad.component.scss']
})
export class SignaturePadComponent {
  @ViewChild('canvas') canvas!: ElementRef;
  signaturePad: SignaturePad | undefined;
  constructor(private snackBar: MatSnackBar, private dialogRef: MatDialogRef<SignaturePadComponent>) { }

  ngAfterViewInit() {
    const canvas: HTMLCanvasElement = this.canvas.nativeElement;
    this.signaturePad = new SignaturePad(canvas);
  }

  clearSignature() {
    if (this.signaturePad) {
      this.signaturePad.clear();
    }
  }

  saveSignature() {
    if (this.signaturePad) {
      const signatureData = this.signaturePad.toDataURL(); // Get the signature as a data URL

      // Create a temporary anchor element to trigger the download
      const link = document.createElement('a');
      link.href = signatureData;
      link.download = 'signature.png';
      link.click();

      // Show a success message in a snackbar
     
    // Show a success message in a snackbar
    this.snackBar.open('Signature saved successfully', 'Dismiss', {
      duration: 2000 // Adjust the duration as needed
    });

    // Close the dialog
    this.dialogRef.close();
      // Close the dialog
      
  }
}
}