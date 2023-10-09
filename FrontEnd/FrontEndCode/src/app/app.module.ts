import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { SignupSiteuserComponent } from './pages/signup-siteuser/signup-siteuser.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatInputModule } from '@angular/material/input';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSortModule } from '@angular/material/sort';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { 
	IgxButtonModule,
	IgxIconModule,
	IgxCardModule,
	IgxRippleModule
 } from "igniteui-angular";
 import {MatDividerModule} from '@angular/material/divider';
import { SiteuserDashboardComponent } from './pages/siteuser-dashboard/siteuser-dashboard.component';
import { SuccessDialogComponent } from './services/success-dialog/success-dialog.component';
import { EditSiteuserComponent } from './pages/edit-siteuser/edit-siteuser.component';
import { EditpasswordComponent } from './pages/editpassword/editpassword.component';
import { AddadminComponent } from './pages/addadmin/addadmin.component';
import { UpdateadminpasswordComponent } from './pages/updateadminpassword/updateadminpassword.component';
import { DeleteadminComponent } from './pages/deleteadmin/deleteadmin.component';
import { AddmealcountformComponent } from './pages/addmealcountform/addmealcountform.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    SignupSiteuserComponent,
    SiteuserDashboardComponent,
    SuccessDialogComponent,
    EditSiteuserComponent,
    EditpasswordComponent,
    AddadminComponent,
    UpdateadminpasswordComponent,
    DeleteadminComponent,
    AddmealcountformComponent

   
  ],
  imports: [
    BrowserModule,
    MatDividerModule,
    MatNativeDateModule,
    MatSnackBarModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatSortModule,
    MatTooltipModule,
    MatPaginatorModule,
    MatSelectModule,
    MatCardModule,
    AppRoutingModule,
    MatTableModule,
    MatDialogModule,
    HttpClientModule,
    FormsModule,
    MatIconModule,
    MatInputModule,
    MatButtonModule,
    NgxMaterialTimepickerModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    IgxButtonModule,
	IgxIconModule,
	IgxCardModule,
	IgxRippleModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
