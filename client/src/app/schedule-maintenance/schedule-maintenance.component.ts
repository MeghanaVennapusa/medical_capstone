import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpService } from '../../services/http.service';

@Component({
  selector: 'app-schedule-maintenance',
  templateUrl: './schedule-maintenance.component.html',
  styleUrls: ['./schedule-maintenance.component.scss']
})

export class ScheduleMaintenanceComponent implements OnInit {

  itemForm !: FormGroup;
  formModel: any = { status: null };
  showError: boolean = false;
  errorMessage: any;
  hospitalList: any[] = [];
  assignModel: any = {};
  showMessage: any;
  responseMessage: any;
  equipmentList: any[] = [];

  constructor(private fb: FormBuilder, private httpService: HttpService) {
    this.itemForm = this.fb.group({
      hospitalId: ['', Validators.required],
      equipmentId: ['', Validators.required],
      scheduledDate: ['', [Validators.required, this.dateValidator]],
      completedDate: ['', [Validators.required, this.dateValidator]],
      description: ['', Validators.required],
      status: ['', Validators.required]
    }, { validators: this.completedAfterScheduledValidator });
  }

  ngOnInit(): void {
    this.getHospital();
  }

  // Validate date is not in the past
  dateValidator(control: any): { [key: string]: boolean } | null {
    const today = new Date().toISOString().split('T')[0];
    if (control.value && control.value < today) {
      return { invalidDate: true };
    }
    return null;
  }

  // Validate completedDate >= scheduledDate
  completedAfterScheduledValidator(group: FormGroup): { [key: string]: boolean } | null {
    const scheduled = group.get('scheduledDate')?.value;
    const completed = group.get('completedDate')?.value;
    if (scheduled && completed && completed < scheduled) {
      return { invalidOrder: true };
    }
    return null;
  }

  getHospital(): void {
    this.httpService.getAllHospitals().subscribe({
      next: (data) => this.hospitalList = data,
      error: () => {
        this.showError = true;
        this.errorMessage = 'Failed to load hospitals';
      }
    });
  }

  onHospitalSelect(): void {
    const hospitalId = this.itemForm.get('hospitalId')?.value;
    if (hospitalId) {
      this.httpService.getEquipmentByHospital(hospitalId).subscribe({
        next: (data) => this.equipmentList = data,
        error: () => {
          this.showError = true;
          this.errorMessage = 'Failed to load equipment';
        }
      });
    }
  }

  onSubmit(): void {
    if (this.itemForm.valid) {
      this.httpService.scheduleMaintenance(this.itemForm.value).subscribe({
        next: () => {
          this.responseMessage = 'Save Successfully';
          this.showMessage = true;
          this.itemForm.reset();
        },
        error: () => {
          this.showError = true;
          this.errorMessage = 'Failed to schedule maintenance';
        }
      });
    }
  }
}

