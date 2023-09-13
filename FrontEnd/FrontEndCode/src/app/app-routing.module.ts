import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { SignupSiteuserComponent } from './pages/signup-siteuser/signup-siteuser.component';
const routes: Routes = [  { path: '', component: LoginComponent },
{ path: 'Dashboard', component: DashboardComponent },
{ path: 'Signup', component: SignupSiteuserComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
