import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  baseUrl = "http://localhost:8080/";
  constructor(private http:HttpClient) { }

  ngOnInit(): void {

  }
  getCustomers():Observable<any>{
    return this.http.get(this.baseUrl+"customers");
  }

}
