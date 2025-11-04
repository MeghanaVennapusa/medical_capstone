import { Component } from '@angular/core';
import { HttpService } from '../../services/http.service';

@Component({
  selector: 'app-dashbaord',
  templateUrl: './dashbaord.component.html',
  styleUrls: ['./dashbaord.component.scss']
})
export class DashbaordComponent {
  
  countHospitals : number=0;
  countOrders: number=0;
  countServices: number=0;
  countSuppliers: number=0;
  countTechnicians: number=0;
  countEquipments: number=0;
  constructor(private http: HttpService){}

 ngOnInit(): void {
  this.http.getAllHospitals().subscribe((data) =>
    this.countHospitals = data.length
  );
  this.http.getorders().subscribe((data) =>
  this.countOrders=data.length
);
this.http.getAllMaintenances().subscribe((data) =>
this.countServices=data.length);
this.http.getSuppliers().subscribe((data) =>
  this.countSuppliers=data);
this.http.getTechnicians().subscribe((data) =>
  this.countTechnicians=data);
this.http.getEquipments().subscribe((data) =>
  this.countEquipments=data);



 }


}
