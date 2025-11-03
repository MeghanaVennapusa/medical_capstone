import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpService } from '../../services/http.service';
import { AuthService } from '../../services/auth.service';
@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  showError: boolean = false;
  errorMessage: any;
  showMessage: any;
  responseMessage: any;
  orderList: any[] = [];
  statusModel: any = { newStatus: null };
  status: any = '';
  statusList: string[] = [];
  showModal: boolean = false;
  searchText: string = '';
  page: number = 1;
  sortAscending: boolean = true;
  constructor(private orderService: HttpService, private router: Router) { }
  ngOnInit(): void {
    this.getOrders();
  }
  getOrders(): void {
    this.orderService.getorders().subscribe({
      next: (response) => {
        this.orderList = response;
        // console.log(this.orderList);
        this.showError = false;
      },
      error: (error) => {
        this.showError = true;
        this.errorMessage = 'Failed to load orders.';
        console.error(error);
      }
    });
  }
  viewDetails(orderId: number): void {
    this.router.navigate(['/orders', orderId]);
  }
  edit(order: any): void {
    this.showModal = true;
    this.statusModel = { orderId: order.id, newStatus: order.status };
  }
  closeModal(): void {
    this.showModal = false;
    this.statusModel = { newStatus: null };
  }
  update(orderId: number, statusClicked: string): void {
    // if (!this.statusModel.newStatus || this.statusModel.newStatus.trim() === '') {
    //   this.showError = true;
    //   this.errorMessage = 'Please select a status.';
    //   return;
    // }

    const order = this.orderList.find(o => o.id === orderId);

    if (!order) {
      this.showError = true;
      this.errorMessage = 'Order not found.';
      return;
    }
    // if (!order.equipment || !order.equipment.id) {
    //   this.showError = true;
    //   this.errorMessage = 'Equipment information is missing for this order.';
    //   console.error('Missing equipment or equipment.id in order:', order);
    //   return;
    // }

    const orderDTO = {
      id: order.id,
      orderDate: order.orderDate,
      quantity: order.quantity,
      status: statusClicked,
      equipmentId: order.equipmentId // or order.equipmentId depending on your model
    };

    this.orderService.UpdateOrderStatus(orderId, orderDTO).subscribe({
      next: (response) => {
        this.responseMessage = 'Status updated.';
        this.showMessage = true;
        this.showError = false;
        this.getOrders();
        this.closeModal();
      },
      error: (error) => {
        this.showError = true;
        this.errorMessage = 'Failed to update order status.';
        console.error(error);
      }
    });
  }

  get filteredOrders() {
    return this.orderList.filter(o =>
      o.equipmentName?.toLowerCase().includes(this.searchText.toLowerCase())
      || o.hospitalName?.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  sortOrderDate() {
    this.orderList.sort((a, b) => {
      const dateA = new Date(a.orderDate).getTime();
      const dateB = new Date(b.orderDate).getTime();
      return this.sortAscending ? dateA - dateB : dateB - dateA;
    });
    this.sortAscending = !this.sortAscending;
  }

}
