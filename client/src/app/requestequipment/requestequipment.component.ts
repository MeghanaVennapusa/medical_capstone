import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { HttpService } from '../../services/http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-requestequipment',
  templateUrl: './requestequipment.component.html',
  styleUrls: ['./requestequipment.component.scss']
})
export class RequestequipmentComponent implements OnInit {
  itemForm!: FormGroup;
  formModel: any = { status: null };
  showError: boolean = false;
  errorMessage: any;
  hospitalList: any[] = [];
  assignModel: any = {};
  showMessage: any;
  responseMessage: any;
  equipmentList: any[] = [];

  constructor(private fb: FormBuilder, private httpService: HttpService, private router:Router) {}

  ngOnInit(): void {
    this.itemForm = this.fb.group({
      hospital: ['', Validators.required],
      quantity: [null, [Validators.required, Validators.min(1)]],
      equipment: ['', Validators.required],
      status: ['', Validators.required],
      orderDate: ['', [Validators.required, this.dateValidator]]
    });

    this.getHospital();
  }

  getHospital(): void {
    this.httpService.getAllHospitals().subscribe({
      next: (response) => {
        this.hospitalList = response;
      },
      error: (err) => {
        this.showError = true;
        this.errorMessage = 'Error loading hospital details';
        console.error(err);
      }
    });
  }

  dateValidator(control: AbstractControl): ValidationErrors | null {
    const selectedDate = new Date(control.value);
    const today = new Date();

    selectedDate.setHours(0, 0, 0, 0);
    today.setHours(0, 0, 0, 0)

    if (selectedDate < today) {
      return { invalidDate: true };
    }
    return null;
  }

  onHospitalSelect(hospital: string): void {
    // Example logic: Load equipment based on hospital
    console.log('Selected Hospital:', hospital);
    this.httpService.getEquipmentsByHospitalName(hospital).subscribe({
      next: (response: any[]) => {
        this.equipmentList = response;
      },
      error: (err: any) => {
        this.showError = true;
        this.errorMessage = 'Error loading in equipment list';
        console.error(err);
      }
    });
  }

  // onHospitalSelect(event: Event): void {
  //   const selectedHospital = (event.target as HTMLSelectElement).value;
  //   console.log('Selected Hospital:', selectedHospital);
  
  //   // Call your logic here
  //   this.httpService.getEquipmentsByHospitalName(selectedHospital).subscribe({
  //     next: (response: any[]) => {
  //       this.equipmentList = response;
  //     },
  //     error: (err: any) => {
  //       this.showError = true;
  //       this.errorMessage = 'Error loading equipment list';
  //       console.error(err);
  //     }
  //   });
  // }


  onSubmit(): void {
    if (this.itemForm.valid) {
      const formData = {
        //hospitalName: this.itemForm.get('hospital')?.value,
        quantity: this.itemForm.get('quantity')?.value,
        //equipmentId: this.itemForm.get('equipment')?.value,
        status: this.itemForm.get('status')?.value,
        orderDate:this.itemForm.get('orderDate')?.value
        //orderDate: this.formatDate(this.itemForm.get('orderDate')?.value) // optional formatting
      };
  
      console.log('Submitting:', formData);
  
      //this.httpService.requestEquipment(formData.equipmentId, formData).subscribe({
        this.httpService.requestEquipment(this.itemForm.get('equipment')?.value, formData).subscribe({
        next: () => {
          this.showMessage = true;
          this.responseMessage = 'Equipment request submitted successfully!';          
          this.itemForm.reset({
            hospital: '',
            equipment: '',
            quantity: '',
            status: '',
            orderDate: ''
          });
          this.router.navigate(['/requestequipment']);
        },
        error: (err) => {
          console.error('Error details:', err);
          this.showError = true;
          this.errorMessage = 'Failed to request equipment';
        }
      });
    } else {
      this.showError = true;
      this.errorMessage = 'Please fill all required fields correctly.';
    }
  }
  

  

  // onSubmit(): void {
  //   if (this.itemForm.valid) {
  //     // this.assignModel = this.itemForm.value;
  //     // console.log('Submitted Data:', this.assignModel);
  //     this.httpService.requestEquipment(this.itemForm.get('equipment')?.value, this.itemForm.value).subscribe({
  //       next : ()=>{
  //         this.showMessage = true;
  //         this.responseMessage = 'Equipment request submitted successfully!';
  //         this.itemForm.reset();
  //       },
  //       error: () => {
  //         this.showError = true;
  //         this.errorMessage = 'Failed to request equipment';
  //       }
  //     })
      
  //   } else {
  //     this.showError = true;
  //     this.errorMessage = 'Please fill all required fields correctly.';
  //   }
  // }


  // onSubmit() {
  //   if (this.itemForm.valid) {
  //     this.httpService.createHospital(this.itemForm.value).subscribe({
  //       next: (response) => {
  //         this.responseMessage = "Hospital created Successfully";
  //         setTimeout(()=>
  //         {
  //           this.itemForm.reset();
  //           this.responseMessage="";
  //         },3000);
  //         this.getHospital();
  //       },
  //       error: (err) => {
  //         this.showError = true;
  //         this.errorMessage = 'Error creating hospital';
  //         setTimeout(()=>
  //           {
  //             this.itemForm.reset();
  //             this.errorMessage="";
  //             this.responseMessage="";
  //             this.showError = false;
  //           },3000);
  //       }
  //     });
  //   }
  // }

 
}
