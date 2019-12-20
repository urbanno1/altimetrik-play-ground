import { Component, OnInit } from '@angular/core';
import { CollegeService } from '../college.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { DomSanitizer } from '@angular/platform-browser';
 
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  collegeList: Array<any> = [];

  search = {
    "distance": "100mi",
    "pageSize": 10,
    "predominant": "2,3",
    "zip": 77005,
    "year": 2017
    }

    year: any;
    page: any = 1;
    perPage: any = 10;

  zipCode: any;
  url: string;

  public searchForm: FormGroup;

  constructor(private collegeService: CollegeService, private sanitizer:DomSanitizer) {
    
    this.year = this.search.year;

    //Displayed when the Code runs.
    this.collegeService.getAllColleges(this.search).subscribe(result => {
      this.collegeList = result;
      this.zipCode = this.search.zip;

    //getting the map url which was displayed on the front-end
      this.url = `https://www.google.com/maps/embed/v1/place?q=${this.zipCode}&key=AIzaSyAohNbXKpZKvgSeR02TC6BVjRUyLiv-1tA`;

      console.log("College" +  this.collegeList);
  });
}

  ngOnInit() {

    this.searchForm = new FormGroup({
      distance: new FormControl('', [
        Validators.required
      ]),

      predominant: new FormControl('', [
        Validators.required,
      ]),

      zip: new FormControl('', [
        Validators.required,
      ]),

      year: new FormControl('', [
        Validators.required,
      ]),

      pageSize: new FormControl('', [
      ]),
     
    });

  }

  // On searching, this method returns what was searched.
  OnSubmitForm() {
    this.zipCode = this.searchForm.get("zip").value;

    this.collegeService.getAllColleges(this.searchForm.value)
    .pipe(first())
    .subscribe((result:any) => {
      this.collegeList = result;

      //getting the map url which was displayed on the front-end
      this.url = `https://www.google.com/maps/embed/v1/place?q=${this.zipCode}&key=AIzaSyAohNbXKpZKvgSeR02TC6BVjRUyLiv-1tA`;
      this.ngOnInit();
    },
    error => {
            
    });
  }

}
