import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { SignupSiteuserComponent } from './pages/signup-siteuser/signup-siteuser.component';
import { SiteuserDashboardComponent } from './pages/siteuser-dashboard/siteuser-dashboard.component';
import { EditSiteuserComponent } from './pages/edit-siteuser/edit-siteuser.component';
import { AddadminComponent } from './pages/addadmin/addadmin.component';
import { DeleteadminComponent } from './pages/deleteadmin/deleteadmin.component';
import { AuthService } from './services/auth.service';
import { AuthGuard } from './services/auth.guard';
import { AddmealcountformComponent } from './pages/addmealcountform/addmealcountform.component';
import { UpdateadminpasswordComponent } from './pages/updateadminpassword/updateadminpassword.component';

const routes: Routes = [  { path: '', component: LoginComponent },
{ path: 'Dashboard', component: DashboardComponent,canActivate: [AuthGuard] },
{ path: 'addadmin', component: AddadminComponent ,canActivate: [AuthGuard]},
{ path: 'login', component: LoginComponent},
{ path: 'deleteadmin', component: DeleteadminComponent ,canActivate: [AuthGuard]},
{ path: 'SiteUserDashboard', component: SiteuserDashboardComponent,canActivate: [AuthGuard]},
{ path: 'edit-siteuser', component: EditSiteuserComponent,canActivate: [AuthGuard]},
{ path: 'addmealcount', component: AddmealcountformComponent,canActivate: [AuthGuard]},
{ path: 'updateadmin', component: UpdateadminpasswordComponent,canActivate: [AuthGuard]},
{ path: 'Signup', component: SignupSiteuserComponent ,canActivate: [AuthGuard]}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
