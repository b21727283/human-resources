import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from "@angular/common/http";

export interface Employee {
  id: number;
  name: string;
  surname: string;
  position: string;
  militaryStatus: 'COMPLETED' | 'EXEMPT' | 'DEFERRED';
  noticePeriod: string;
  phoneNumber: string;
  email: string;
  cv: string; // http://localhost:8080/employee/{id}/cv
}

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiUrl = 'http://localhost:8080/employee';

  constructor(private http: HttpClient) {
  }

  addEmployee(employee: any): Observable<Employee> {
    const formData = new FormData();
    formData.append('name', employee.name);
    formData.append('surname', employee.surname);
    formData.append('position', employee.position);
    formData.append('militaryStatus', employee.militaryStatus);
    formData.append('noticePeriod', employee.noticePeriod);
    formData.append('phoneNumber', employee.phoneNumber);
    formData.append('email', employee.email);
    formData.append('cv', employee.cv);

    return this.http.post<Employee>(`${this.apiUrl}/create`, formData);
  }

  getEmployeeCv(id: number): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/${id}/cv`, {responseType: 'blob'});
  }

  getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiUrl}/get/all`);
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiUrl}/get/${id}`);
  }

  updateEmployee(id: number, employee: any): Observable<Employee> {
    const formData = new FormData();
    formData.append('name', employee.name);
    formData.append('surname', employee.surname);
    formData.append('position', employee.position);
    formData.append('militaryStatus', employee.militaryStatus);
    formData.append('noticePeriod', employee.noticePeriod);
    formData.append('phoneNumber', employee.phoneNumber);
    formData.append('email', employee.email);

    if (employee.cv != null) {
      formData.append('cv', employee.cv);
    }

    return this.http.put<Employee>(`${this.apiUrl}/update/${id}`, formData);
  }

  deleteEmployee(id: number): Observable<Employee> {
    return this.http.delete<Employee>(`${this.apiUrl}/delete/${id}`);
  }
}
