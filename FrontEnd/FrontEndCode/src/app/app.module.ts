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

import { 
	IgxButtonModule,
	IgxIconModule,
	IgxCardModule,
	IgxRippleModule
 } from "igniteui-angular";
 import {MatDividerModule} from '@angular/material/divider';
import { SiteuserDashboardComponent } from './pages/siteuser-dashboard/siteuser-dashboard.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    SignupSiteuserComponent,
    SiteuserDashboardComponent
  ],
  imports: [
    BrowserModule,
    MatDividerModule,
    MatPaginatorModule,
    MatCardModule,
    AppRoutingModule,
    MatTableModule,
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
