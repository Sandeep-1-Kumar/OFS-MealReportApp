import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { SignupSiteuserComponent } from './pages/signup-siteuser/signup-siteuser.component';
import { SiteuserDashboardComponent } from './pages/siteuser-dashboard/siteuser-dashboard.component';
const routes: Routes = [  { path: '', component: LoginComponent },
{ path: 'Dashboard', component: DashboardComponent },
{ path: 'SiteUserDashboard', component: SiteuserDashboardComponent},
{ path: 'Signup', component: SignupSiteuserComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
