import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpService } from '../../services/http.service';

declare var bootstrap: any;

@Component({
  selector: 'app-maintenance',
  templateUrl: './maintenance.component.html',
  styleUrls: ['./maintenance.component.scss']
})
export class MaintenanceComponent implements OnInit {

  formModel: any = { status: null };
  showError: boolean = false;
  errorMessage: any;
  hospitalList: any[] = [];
  assignModel: any = {};
  itemForm: FormGroup;
  showMessage: any;
  responseMessage: any;
  maintenanceList: any[] = [];
  maintenanceObj: any = {};
  editModal: any;

  constructor(private httpService: HttpService, private fb: FormBuilder) {
    this.itemForm = this.fb.group({
      scheduledDate: ['', [Validators.required , this.dateValidator]],
      completedDate: ['', [Validators.required , this.dateValidator]],
      description: ['', Validators.required],
      status: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.getMaintenance();
    this.editModal = new bootstrap.Modal(document.getElementById('editModal'));
  }

  dateValidator(control: any): { [key: string]: boolean } | null {
    const today = new Date().toISOString().split('T')[0];
    if (control.value && control.value < today) {
      return { invalidDate: true };
    }
    return null;
  }

  getMaintenance(): void {
    this.httpService.getAllMaintenances().subscribe({
      next: (data) => this.maintenanceList = data,
      error: () => {
        this.showError = true;
        this.errorMessage = 'Failed to load maintenance records';
      }
    });
  }

  viewDetails(maintenance: any): void {
    this.edit(maintenance); // For compatibility
  }

  edit(maintenance: any): void {
    this.maintenanceObj = maintenance;
    this.itemForm.patchValue({
      scheduledDate: maintenance.scheduledDate?.split('T')[0],
      completedDate: maintenance.completedDate?.split('T')[0],
      description: maintenance.description,
      status: maintenance.status
    });
    this.editModal.show();
  }

  update(): void {
    if (this.maintenanceObj) {
      const updated = { ...this.maintenanceObj, ...this.itemForm.value };
      this.httpService.updateMaintenance(this.maintenanceObj.id , updated).subscribe({
        next: () => {
          this.responseMessage = 'Maintenance updated successfully';
          this.showMessage = true;
          this.editModal.hide();
          this.getMaintenance();
        },
        error: () => {
          this.showError = true;
          this.errorMessage = 'Update failed';
        }
      });
    }
  }
}