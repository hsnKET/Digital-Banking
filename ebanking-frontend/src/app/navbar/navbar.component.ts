import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../services/token-storage.service";
import {Router} from "@angular/router";
import Swal from "sweetalert2";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLogin = false;

  constructor( private tokenStorage: TokenStorageService,
               private route:Router) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()){
      this.isLogin = true;
    }

    // if (this.isLogin){
    //   this.route.navigateByUrl("/customers")
    // }
    // else {
    //   this.route.navigateByUrl("/login")
    // }
  }

  logout() {
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't logout!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, logout!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.tokenStorage.signOut();
        Swal.fire(
          'Logged out!',
          '',
          'success'
        )
      }
    })
  }
}
