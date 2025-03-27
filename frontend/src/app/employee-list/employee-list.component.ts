import {Component, OnInit} from '@angular/core';
import {Employee, EmployeeService} from '../employee.service';
import {IonicModule, ModalController} from "@ionic/angular";
import {CommonModule} from "@angular/common";
import {EmployeeFormComponent} from "../employee-form/employee-form.component";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss'],
  imports: [IonicModule, CommonModule, FormsModule],
  standalone: true
})
export class EmployeeListComponent implements OnInit {
  employees: Employee[] = [];
  filteredEmployees: Employee[] = [];
  displayedEmployees: Employee[] = [];
  searchQuery: string = '';
  selectedField: string = 'name';
  currentPage: number = 1;
  itemsPerPage: number = 10;

  constructor(private employeeService: EmployeeService, private modalController: ModalController) {
  }

  ngOnInit() {
    this.loadEmployees();
  }

  loadEmployees() {
    this.employeeService.getAllEmployees().subscribe({
      next: (employees) => {
        this.employees = employees;
        this.filteredEmployees = employees;
        this.updateDisplayedEmployees();
      },
      error: (err) => {
        console.error('Error loading employees:', err);
      }
    });
  }

  async openForm(employee: Employee | null = null) {
    const modal = await this.modalController.create({
      component: EmployeeFormComponent,
      componentProps: {employee}
    });

    modal.onDidDismiss().then(() => {
      this.loadEmployees();
    });

    return await modal.present();
  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id).subscribe(() => {
      this.currentPage = 1;
      this.loadEmployees();
    });
  }

  updateDisplayedEmployees() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.displayedEmployees = this.filteredEmployees.slice(startIndex, endIndex);
  }

  nextPage() {
    if (this.currentPage * this.itemsPerPage < this.filteredEmployees.length) {
      this.currentPage++;
      this.updateDisplayedEmployees();
    }
  }

  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updateDisplayedEmployees();
    }
  }

  filterEmployees() {
    const query = this.searchQuery.toLowerCase();
    this.filteredEmployees = this.employees.filter(employee => {
      const fieldValue = (employee as any)[this.selectedField]?.toLowerCase();
      return fieldValue?.includes(query);
    });
    this.currentPage = 1;
    this.updateDisplayedEmployees();
  }

  downloadCv(id: number) {
    this.employeeService.getEmployeeCv(id).subscribe(blob => {
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'cv';
      a.click();
      window.URL.revokeObjectURL(url);
    });
  }
}
