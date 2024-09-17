import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { SignUpUserRequest } from 'src/app/models/interfaces/user/SignUpUserRequest';
import { SignUpUserResponse } from 'src/app/models/interfaces/user/SignUpUserResponse';
import { AuthRequest } from 'src/app/models/interfaces/user/auth/AuthRequest';
import { UserService } from 'src/app/services/user/user.service';
import { CookieService } from 'ngx-cookie-service';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  loginCard = true;

  loginForm = this.formBuilder.group({
    email: ['', Validators.required],
    password: ['', Validators.required],
  });

  signupForm = this.formBuilder.group({
    name: ['', Validators.required],
    email: ['', Validators.required],
    password: ['', Validators.required],
  });

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private cookieService: CookieService,
    private messageService: MessageService,
    private router: Router
  ) {}

  onSubmitLoginForm(): void {
    if (this.loginForm.value && this.loginForm.valid){
      this.userService.authUser(this.loginForm.value as AuthRequest)
      .subscribe({
        next: (response) => {
          this.cookieService.set('USER_INFO', response?.token);
          this.loginForm.reset();
          this.router.navigate(['/dashboard']);

          this.messageService.add({
            severity: 'success',
            summary: 'Sucesso!',
            detail: 'Login efetuado com sucesso!',
            life: 2000
          })
        },
        error: (err) => {
          console.log(err)

          this.messageService.add({
            severity: 'error',
            summary: 'Erro',
            detail: 'Erro ao efetuar login',
            life: 2000
          })
        },
      })
    }
  }

  onSubmitSignupForm(): void {
    if (this.signupForm.value && this.signupForm.valid) {
      this.userService
        .signUpUser(this.signupForm.value as SignUpUserRequest)
        .subscribe({
          next: (response) => {
            if (response) {
              alert('Usuário teste criado com sucesso!');
              this.signupForm.reset();
              this.loginCard = true;

              this.messageService.add({
                severity: 'success',
                summary: 'Sucesso!',
                detail: 'Erro ao cadastrar conta',
                life: 2000
              })

            }
          },
          error: (err) => {
            console.log(err);
            this.messageService.add({
              severity: 'error',
              summary: 'Erro!',
              detail: 'Erro ao cadastrar conta',
              life: 2000
            })
          },
        });
    }
  }
}
