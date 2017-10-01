import {Injectable} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';

@Injectable()
export class ProfileService {

  constructor(private http: Http) {}
  private handleError(errorResponse: Response) {
    console.log(errorResponse.statusText);
    return Observable.throw(errorResponse.json().error || 'Server error');

  }

  findUser(login :string ) {
    
   const url = `/api/user/read/${login}`;
   
       return this.http.get(url)
         .map((resp: Response) => {
           return resp.json();
         }).catch(this.handleError);
     }
  
}
