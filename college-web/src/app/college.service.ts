import { Injectable } from '@angular/core';
import { HttpClient  } from  '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class CollegeService {
  
  constructor(private http: HttpClient) {
  }

  // this connects with rest api to get the list of colleges.
  getAllColleges(searchObj: object) : Observable<any> {
    return this.http.post<any>(environment.baseUrl.API_GATEWAY + "api/college/search", searchObj);
  }
}
