import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GetAllStudentResponse } from '../interfaces/student/GetAllStudentResponse';
import { environment } from '../config/environment';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }
  private API_URL = environment.API_URL;

  public getAllStudents(): Observable<GetAllStudentResponse[]> {
    return this.http.get<GetAllStudentResponse[]>(`${this.API_URL}/student`);
  }
}
