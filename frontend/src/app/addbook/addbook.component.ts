import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Book } from '../book';
import { BookstoreService } from '../bookstore.service';

@Component({
  selector: 'app-addbook',
  templateUrl: './addbook.component.html',
  styleUrls: ['./addbook.component.css']
})
export class AddbookComponent {

  book: Book = new Book();

  constructor(private _service: BookstoreService,private _route:Router) {}

  addbookFormSubmit(form: NgForm) {

    let book = new Book();
    book.id = form.value.id;
    book.name = form.value.name;
    book.author = form.value.author;
    book.publishYear = form.value.publishYear;
    book.price = form.value.price;

    console.log(book);
    
    this._service.addBookToServer(book).subscribe(
      data => data,
    );

    this.RedirectToViewBook();
    
  }

  RedirectToViewBook(){
    this._route.navigate(['/viewbook']);
  }

}
