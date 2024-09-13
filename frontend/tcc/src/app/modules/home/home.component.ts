import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  login = true;

  constructor(private formBuilder: FormBuilder){}

  loginForm = this.formBuilder.group({
    login: ['', Validators.required],
    senha: ['', Validators.required]
  })

  signUpForm = this.formBuilder.group({
    nome: ['', Validators.required],
    login: ['', Validators.required],
    senha: ['', Validators.required]
  })

  signUp(){

  }

  loginAuth(){

  }
}
