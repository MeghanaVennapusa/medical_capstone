import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  private serverName = `${environment.apiUrl}`;

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
  }

  login(credentials: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
    return this.http.post<any>(`${this.serverName}/api/user/login`, credentials, { headers });
  }

  register(credentials: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
    return this.http.post<any>(`${this.serverName}/api/user/register`, credentials, { headers });
  }

  createHospital(hospital: any): Observable<any> {
    return this.http.post(`${this.serverName}/api/hospital/create`, hospital, {
      headers: this.getHeaders()
    });
  }

  getHospital(): Observable<any> {
    return this.http.get<any>(`${this.serverName}/api/hospitals`, {
      headers: this.getHeaders()
    });
  }

  addEquipment(equipment: any, hospitalId: any): Observable<any> {
    return this.http.post<any>(`${this.serverName}/api/hospital/equipment?hospitalId=${hospitalId}`, equipment , {
      headers: this.getHeaders()
    });
  }

  getAllMaintenances(): Observable<any[]> {
    return this.http.get<any[]>(`${this.serverName}/api/technician/maintenance`, {
      headers: this.getHeaders()
    });
  }

  updateMaintenance(maintenance: any, maintenanceId: number): Observable<any> {
    return this.http.put<any>(`${this.serverName}/api/technician/maintenance/update/${maintenanceId}`, maintenance, {
      headers: this.getHeaders()
    });
  }

  getMaintenanceById(maintenanceId: number): Observable<any> {
    return this.http.get<any>(`${this.serverName}/api/technician/maintenance/${maintenanceId}`, {
      headers: this.getHeaders()
    });
  }

  getAllHospitals(): Observable<any[]> {
    return this.http.get<any[]>(`${this.serverName}/api/hospitals`, {
      headers: this.getHeaders()
    });
  }

  getEquipmentByHospital(hospitalId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.serverName}/api/equipment/hospital/${hospitalId}`, {
      headers: this.getHeaders()
    });
  }

  scheduleMaintenance(data: any, equipmentId: any): Observable<any> {
    return this.http.post<any>(`${this.serverName}/api/hospital/maintenance/schedule?equipmentId=${equipmentId}`, data, {
      headers: this.getHeaders()
    });
  }

  getMaintenance(): Observable<any> {
    return this.http.get<any>(`${this.serverName}/api/technician/maintenance`, {
      headers: this.getHeaders()
    });
  }

  orderEquipment(order: any, equipmentId: any): Observable<any> {
    return this.http.post<any>(`${this.serverName}/api/hospital/order?equipmentId=${equipmentId}`, order, {
      headers: this.getHeaders()
    });
  }

  getorders(): Observable<any> {
    return this.http.get<any>(`${this.serverName}/api/supplier/orders`, {
      headers: this.getHeaders()
    });
  }

  UpdateOrderStatus(newStatus: string, orderId: number): Observable<any> {
    return this.http.put<any>(`${this.serverName}/api/supplier/order/update/${orderId}?newStatus=${newStatus}`, {},
      {headers: this.getHeaders()}
    );
  }
}
