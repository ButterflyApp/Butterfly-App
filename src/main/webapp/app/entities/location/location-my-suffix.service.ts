import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { LocationMySuffix } from './location-my-suffix.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class LocationMySuffixService {

    private resourceUrl = 'api/locations';

    constructor(private http: Http) { }

    create(location: LocationMySuffix): Observable<LocationMySuffix> {
        const copy = this.convert(location);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(location: LocationMySuffix): Observable<LocationMySuffix> {
        const copy = this.convert(location);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<LocationMySuffix> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(location: LocationMySuffix): LocationMySuffix {
        const copy: LocationMySuffix = Object.assign({}, location);
        return copy;
    }
    private handleError(errorResponse: Response) {
        console.log(errorResponse.statusText);
        return Observable.throw(errorResponse.json().error || 'Server error');

    }
    findUser(login: string) {

        const url = `/api/user/read/${login}`;

        return this.http.get(url)
            .map((resp: Response) => {
                return resp.json();
            }).catch(this.handleError);
    }
}
