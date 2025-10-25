import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  private baseUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) {}

  // Get all maintenance records
  getAllMaintenances(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/api/technician/maintenance`);
  }

  // Update maintenance by ID
  updateMaintenance(maintenanceId: number, maintenance: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/api/technician/maintenance/update/${maintenanceId}`, maintenance);
  }

  // Optional: Get maintenance by ID (if needed later)
  getMaintenanceById(maintenanceId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/api/technician/maintenance/${maintenanceId}`);
  }

  // Get all hospitals
getAllHospitals(): Observable<any[]> {
  return this.http.get<any[]>(`${this.baseUrl}/api/hospitals`);
}

// Get equipment by hospital ID
getEquipmentByHospital(hospitalId: number): Observable<any[]> {
  return this.http.get<any[]>(`${this.baseUrl}/api/equipment/hospital/${hospitalId}`);
}

// Schedule maintenance
scheduleMaintenance(data: any): Observable<any> {
  return this.http.post<any>(`${this.baseUrl}/api/technician/maintenance`, data);
}
}