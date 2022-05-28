import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../model/customer.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http:HttpClient) { }

  ngOnInit(): void {

  }
  getCustomers():Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(environment.baseUrl+"customers");
  }

  searchCustomers(keyword:String):Observable<Array<Customer>>{
    return this.http
      .get<Array<Customer>>(environment.baseUrl+"customers/search?keyword="+keyword);
  }

  addCustomers(customer:Customer):Observable<Customer>{
    return this.http
      .post<Customer>(environment.baseUrl+"customers/",customer);
  }

  deleteCustomers(id:number){
    return this.http
      .delete(environment.baseUrl+"customers/"+id);
  }
}
