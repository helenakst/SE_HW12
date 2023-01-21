import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from './book';

@Injectable({
  providedIn: 'root'
})
export class BookstoreService {

  constructor(private _http:HttpClient) { }

  getBooksFromServer():Observable<any> {
    return this._http.get<any>("http://localhost:8080/bookStore")
  }

  getOldestBookFromServer():Observable<any> {
    return this._http.get<any>("http://localhost:8080/bookStore/oldest")
  }

  getLatestBookFromServer():Observable<any> {
    return this._http.get<any>("http://localhost:8080/bookStore/latest")
  }

  addBookToServer(book:Book):Observable<any> {
    return this._http.post("http://localhost:8080/bookStore", book);
  }

  deleteBookFromServer(id: Number) {
    return this._http.delete("http://localhost:8080/bookStore/" + id);
  }
}
