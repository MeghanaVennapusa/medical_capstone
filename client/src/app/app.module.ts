import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';


import { RegistrationComponent } from './registration/registration.component';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { HttpService } from '../services/http.service';
import { DashbaordComponent } from './dashbaord/dashbaord.component';



import { CreatehospitalComponent } from './createhospital/createhospital.component';
import { ScheduleMaintenanceComponent } from './schedule-maintenance/schedule-maintenance.component';
import { RequestequipmentComponent } from './requestequipment/requestequipment.component';
import { MaintenanceComponent } from './maintenance/maintenance.component';
import { OrdersComponent } from './orders/orders.component';
import { AuthInterceptor } from './auth.interceptors';
import { PagenotfoundComponent } from './utilities/pagenotfound/pagenotfound.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
      RegistrationComponent,
      DashbaordComponent,
  
    
      CreatehospitalComponent,
      ScheduleMaintenanceComponent,
      RequestequipmentComponent,
      MaintenanceComponent,
      OrdersComponent,
      ForgotpasswordComponent,
      PagenotfoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxPaginationModule
  ],
  providers: [HttpService,HttpClientModule,
    
{
  provide: HTTP_INTERCEPTORS,
  useClass: AuthInterceptor,
  multi: true
},

   ],
  bootstrap: [AppComponent]
})
export class AppModule { }
