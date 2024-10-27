import { Component } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {

  sidebarVisible = true;

  hello () {
    console.log("ola")
  }

  items = [
    { label: 'Home', icon: 'pi pi-home'},
    { label: 'Perfil', icon: 'pi pi-user' },
    { label: 'Alunos', icon: 'pi pi-users' },
    { label: 'Professores', icon: 'pi pi-user-edit' },
    { label: 'Turmas', icon: 'pi pi-list' },
    { label: 'Exames de Graduação', icon: 'pi pi-star' },
    { label: 'Financeiro', icon: 'pi pi-chart-bar' },
    { label: 'Relatórios', icon: 'pi pi-file' },
  ];
}
