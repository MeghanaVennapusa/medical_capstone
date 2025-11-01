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
  filteredMaintenanceList: any[] = [];
  maintenanceObj: any = {};
  editModal: any;

  searchTerm: string = '';
  sortDirection: { [key: string]: 'asc' | 'desc' } = { scheduledDate: 'asc', completedDate: 'asc' };

  
  currentPage: number = 1;
  itemsPerPage: number = 4;


  constructor(private httpService: HttpService, private fb: FormBuilder) {
    this.itemForm = this.fb.group({
      scheduledDate: [{value : '' , disabled : true}],
      completedDate: [''],
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
      next: (data) => {
        this.maintenanceList = data;
        this.filteredMaintenanceList = [...this.maintenanceList];
      },
      error: () => {
        this.showError = true;
        this.errorMessage = 'Failed to load maintenance records';
      }
    });
  }

  filterMaintenance(): void {
    const term = this.searchTerm.toLowerCase();
    this.filteredMaintenanceList = this.maintenanceList.filter(m =>
      m.equipment?.hospital?.name?.toLowerCase().includes(term)
    );
    this.currentPage = 1;
  }

  sortBy(column: string): void {
    const direction = this.sortDirection[column];
    this.filteredMaintenanceList.sort((a, b) => {
      const dateA = new Date(a[column]);
      const dateB = new Date(b[column]);
      return direction === 'asc' ? dateA.getTime() - dateB.getTime() : dateB.getTime() - dateA.getTime();
    });
    this.sortDirection[column] = direction === 'asc' ? 'desc' : 'asc';
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
      this.httpService.updateMaintenance(this.maintenanceObj.id, updated).subscribe({
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
  
  get paginatedMaintenance(): any[] {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    return this.filteredMaintenanceList.slice(startIndex, endIndex);
  }

  get totalPages(): number {
    return Math.ceil(this.filteredMaintenanceList.length / this.itemsPerPage);
  }

  changePage(page: number): void {
    if (page >= 1 && page <= this.totalPages) {
        this.currentPage = page;
        }
    }
}