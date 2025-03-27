import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {IonicModule, ModalController} from '@ionic/angular';
import {Employee, EmployeeService} from '../employee.service';
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.scss'],
  imports: [IonicModule, CommonModule, ReactiveFormsModule],
  standalone: true
})
export class EmployeeFormComponent implements OnInit {
  @Input() employee: Employee | null = null;
  employeeForm: FormGroup;
  fileName: string | null = null;

  constructor(private fb: FormBuilder, private employeeService: EmployeeService, private modalController: ModalController) {
    this.employeeForm = this.fb.group({
      id: [null],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      position: ['', Validators.required],
      militaryStatus: ['', Validators.required],
      noticePeriod: ['', Validators.required],
      phoneNumber: ['', [Validators.required, Validators.pattern(/^\+90-5\d{2}-\d{3}-\d{4}$/)]],
      email: ['', [Validators.required, Validators.email]],
      cv: [null, Validators.required]
    });
  }

  // cv is unrequired to update employee
  ngOnInit() {
    if (this.employee) {
      this.employeeForm.patchValue(this.employee);
      this.employeeForm.get('cv')?.clearValidators();
    } else {
      this.employeeForm.get('cv')?.setValidators(Validators.required);
    }
    this.employeeForm.get('cv')?.updateValueAndValidity();
  }

  triggerFileInput() {
    const fileInput = document.getElementById('fileInput') as HTMLInputElement;
    fileInput.click();
  }

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.fileName = input.files[0].name;
      this.employeeForm.patchValue({ cv: input.files[0] });
      this.employeeForm.get('cv')?.updateValueAndValidity();
    } else {
      this.fileName = null;
      this.employeeForm.patchValue({ cv: null });
      this.employeeForm.get('cv')?.updateValueAndValidity();
    }
  }

  onSubmit() {
    if (this.employeeForm.valid) {
      const employee: Employee = this.employeeForm.value;
      if (employee.id) {
        this.employeeService.updateEmployee(employee.id, employee).subscribe(updatedEmployee => {
          this.modalController.dismiss();
        });
      } else {
        this.employeeService.addEmployee(employee).subscribe(newEmployee => {
          this.modalController.dismiss();
        });
      }
    }
  }

  onCancel() {
    this.modalController.dismiss();
  }
}
