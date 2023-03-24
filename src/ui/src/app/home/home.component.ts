import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  content?: string;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.content =
      'Welcome to my app! This is the home page that welcomes you first.';
  }
}
