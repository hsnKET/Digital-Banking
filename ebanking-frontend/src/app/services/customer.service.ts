import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer, CustomerPage} from "../model/customer.model";
import {environment} from "../../environments/environment";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http:HttpClient) { }

  ngOnInit(): void {

  }
  getCustomers():Observable<CustomerPage>{

    return this.http.get<CustomerPage>(environment.baseUrl+"customers/searchs");
  }

  searchCustomers(keyword:String,page?:number,size?:number):Observable<CustomerPage>{
    return this.http
      .get<CustomerPage>(environment.baseUrl+"customers/searchs?keyword="+keyword+"&page="+page+"&size="+size);
  }

  addCustomers(customer:Customer):Observable<Customer>{
    return this.http
      .post<Customer>(environment.baseUrl+"customers/",customer);
  }

  deleteCustomers(id:number){
    return this.http
      .delete(environment.baseUrl+"customers/"+id)
  }
}
