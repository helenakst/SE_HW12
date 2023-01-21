import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookstoreService } from '../bookstore.service';
import { Book } from '../book';

@Component({
  selector: 'app-viewbook',
  templateUrl: './viewbook.component.html',
  styleUrls: ['./viewbook.component.css']
})
export class ViewbookComponent implements OnInit{

  books: Array<Book> = [];

  oldestBook: Book = new Book();
  latestBook: Book = new Book();

  showOldest: boolean = false;
  showLatest: boolean = false

  constructor(private _service: BookstoreService, private _route:Router) {}

  ngOnInit(): void {
    this.getbooks();
    this.getOldestBook();
    this.getLatestBook();
  }

  getbooks() {
    //when the page is open, we want to call the API

    this._service.getBooksFromServer().subscribe(
      data => this.books = data,
    )
  }

  getOldestBook() {
    this._service.getOldestBookFromServer().subscribe(
      data => this.oldestBook = data,
    )
  }

  getLatestBook() {
    this._service.getLatestBookFromServer().subscribe(
      data => this.latestBook = data,
    )
  }

  DeleteBook(id: Number) {
    this._service.deleteBookFromServer(id).subscribe();
    window.location.reload();
  }

  RedirectToAddBook() {
    this._route.navigate(['/addbook'])
  }

  RedirectToViewBook() {
    this._route.navigate(['/viewbook'])
  }

  ShowOldestBook() {
    this.showOldest = true;
    this.showLatest = false;
  }

  ShowLatestBook() {
    this.showOldest = false;
    this.showLatest = true;
  }

  ShowAllBooks() {
    this.showOldest = false;
    this.showLatest = false;
  }

}
