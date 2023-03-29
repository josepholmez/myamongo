import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PasswordWrapper } from '../model/password.wrapper';
import { User } from '../model/user';
import { environment } from './../../environments/environment';
import { AuthService } from './auth.service';

const USERS_URL = environment.usersUrl; // http://localhost:5000/api/v1/users

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private headerObj;

  constructor(private http: HttpClient, private authService: AuthService) {
    this.headerObj = this.authService.getHeaderObject();
  }

  async getAdminBoard() {
    return this.http.get(USERS_URL, this.headerObj);
  }

  async getUserByUsername(username: string) {
    let url = USERS_URL + `'/username`;
    let httpOptions = this.authService.addHeaderAndParam('username', username);
    return this.http.get(url, httpOptions);
  }

  async getUserById(id: string): Promise<Observable<User>> {
    let url = USERS_URL + `/${id}`;
    return this.http.get<User>(url, this.headerObj);
  }

  async updatePassword(passWrapper: PasswordWrapper) {
    let url = USERS_URL + `/pass`;
    return this.http.put(url, passWrapper);
  }

  async getAllUsers(): Promise<Observable<User[]>> {
    return this.http.get<User[]>(USERS_URL, this.headerObj);
  }

  async updateUser(user: User) {
    return this.http.put(USERS_URL, user, this.headerObj);
  }

  async updateUserById(id: string, user: User): Promise<Observable<Object>> {
    let url = USERS_URL + `/${id}`;
    return this.http.put(url, user, this.headerObj);
  }

  async deleteUser(user: User) {
    let url = USERS_URL + `/${user.id}`;
    return this.http.delete(url, this.headerObj);
  }
}
