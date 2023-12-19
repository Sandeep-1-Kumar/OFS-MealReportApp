import { Component , ElementRef, Input,  OnInit, ViewChild } from '@angular/core';
import { UserDataService } from 'src/app/services/user-data.service';
import * as pdfMake from 'pdfmake/build/pdfmake';
import * as pdfFonts from 'pdfmake/build/vfs_fonts';
import { MatDialogRef } from '@angular/material/dialog';
import { jsPDF } from "jspdf";


(pdfMake.vfs as any ) = pdfFonts.pdfMake.vfs;

@Component({
  selector: 'app-pdf-viewer',
  templateUrl: './pdf-viewer.component.html',
  styleUrls: ['./pdf-viewer.component.scss']
})
export class PdfViewerComponent implements  OnInit {
@ViewChild('content',{static: false}) el!: ElementRef;
  @Input() pdfSrc: string='';
  @Input() rowData: any;
  
  ngOnInit(): void {
    
   
  }
  
  
  constructor(
    private UserDataService:UserDataService,public dialogRef: MatDialogRef<PdfViewerComponent>
    // ...
  ) {
    // Get rowData from the service
    this.rowData = this.UserDataService.getRowData();
  }
  downloadPdf() {
    const pdf = new jsPDF('p', 'mm', 'a4');
    
    
    pdf.html(this.el.nativeElement,{callback: (pdf)=>{pdf.save("demo.pdf")}});
   
  }
  
  

  closeViewer() {
    this.dialogRef.close();
  }

}
