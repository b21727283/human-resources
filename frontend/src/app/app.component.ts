import {Component} from '@angular/core';
import {CommonModule} from "@angular/common";
import {IonicModule} from '@ionic/angular';
import {EmployeeListComponent} from "./employee-list/employee-list.component";

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  standalone: true,
  imports: [CommonModule, IonicModule, EmployeeListComponent]
})
export class AppComponent {
  constructor() {
  }
}
