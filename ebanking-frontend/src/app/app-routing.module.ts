import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomersComponent} from "./customers/customers.component";
import {AccountsComponent} from "./accounts/accounts.component";
import {NewCustomerComponent} from "./new-customer/new-customer.component";
import {CustomerAccountsComponent} from "./customer-accounts/customer-accounts.component";
import {LoginComponent} from "./login/login.component";
import {LogoutComponent} from "./logout/logout.component";
import {AuthGuard} from "./helpers/auth-guard";
import {NewAccountComponent} from "./new-account/new-account.component";

const routes: Routes = [
  {path:"customers",component:CustomersComponent,canActivate:[AuthGuard]},
  {path:"accounts",component:AccountsComponent,canActivate:[AuthGuard]},
  {path:"new-customer",component:NewCustomerComponent,canActivate:[AuthGuard]},
  {path:"customer-accounts/:id",component:CustomerAccountsComponent,canActivate:[AuthGuard]},
  {path:"accounts/:id",component:AccountsComponent,canActivate:[AuthGuard]},
  {path:"login",component:LoginComponent},
  {path:"logout",component:LogoutComponent},
  {path:"new-account/:id",component:NewAccountComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
