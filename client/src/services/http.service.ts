import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment.development';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  public serverName=environment.apiUrl;
  constructor(private http:HttpClient)
  {

  }

  login(credentials: any): Observable<any> {
    return this.http.post<any>(`${this.serverName}/api/user/login`, credentials);
  }

  createHospital(hospital:any) :Observable<any>
  {
   return this.http.post<any>(`${this.serverName}/api/hospitals`,hospital);
  }
  getHospital() :Observable<any>
  {
    return this.http.get<any>(`${this.serverName}/api/hospitals`);
  }
  addEquipment(equipment :any):Observable<any> 
  {
    return this.http.post<any>(`${this.serverName}/api/hospital/equipment`,equipment.hospitalId,equipment);
  }
  }
