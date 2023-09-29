import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { SignupSiteuserComponent } from './pages/signup-siteuser/signup-siteuser.component';
import { SiteuserDashboardComponent } from './pages/siteuser-dashboard/siteuser-dashboard.component';
import { EditSiteuserComponent } from './pages/edit-siteuser/edit-siteuser.component';
import { AddadminComponent } from './pages/addadmin/addadmin.component';
import { DeleteadminComponent } from './pages/deleteadmin/deleteadmin.component';
const routes: Routes = [  { path: '', component: LoginComponent },
{ path: 'Dashboard', component: DashboardComponent },
{ path: 'addadmin', component: AddadminComponent },
{ path: 'login', component: LoginComponent },
{ path: 'deleteadmin', component: DeleteadminComponent },
{ path: 'SiteUserDashboard', component: SiteuserDashboardComponent},
{ path: 'edit-siteuser', component: EditSiteuserComponent},
{ path: 'Signup', component: SignupSiteuserComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
