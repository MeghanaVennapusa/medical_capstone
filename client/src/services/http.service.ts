import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment.development';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  public serverName = environment.apiUrl;

  constructor(private http:HttpClient){}

  getOrders():Observable<any>{
    return this.http.get<any>(`${this.serverName}/orders`);
  }

  updateOrderStatus(orderId:number,newStatus:string):Observable<any>{
    return this.http.put<any>(`${this.serverName}/order/update/${orderId}`,{status:newStatus});
  }
  
}
