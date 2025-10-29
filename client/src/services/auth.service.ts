import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(!!localStorage.getItem('authToken'));
  private userRole = new BehaviorSubject<string | null>(localStorage.getItem('role'));

  loggedIn$ = this.loggedIn.asObservable();
  userRole$ = this.userRole.asObservable();

  constructor() {}

  // Save token and update state
  saveToken(token: string) {
    localStorage.setItem('authToken', token);
    this.loggedIn.next(true);
  }

  setRole(role: string) {
    localStorage.setItem('role', role);
    this.userRole.next(role);
  }

  getLoginStatus(): boolean {
    return !!localStorage.getItem('authToken');
  }

  getRole(): string | null {
    return localStorage.getItem('role');
  }

  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  logout(): void {
    localStorage.removeItem('authToken');
    localStorage.removeItem('role');
    this.loggedIn.next(false);
    this.userRole.next(null);
  }
}
