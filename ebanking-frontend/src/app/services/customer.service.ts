import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../model/customer.model";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  baseUrl = "http://localhost:8080/";
  constructor(private http:HttpClient) { }

  ngOnInit(): void {

  }
  getCustomers():Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(this.baseUrl+"customers");
  }

}
