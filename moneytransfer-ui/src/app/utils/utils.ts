import {NgbDate} from '@ng-bootstrap/ng-bootstrap';
import {isNumber, padNumber} from './filter/ng-bootstrap-util';

export class  Utils {

  public static ngbDateToString(date: NgbDate): string {
    return date ? `${isNumber(date.day) ? padNumber(date.day) : ''}/${isNumber(date.month) ? padNumber(date.month) : ''}/${date.year}` : '';
  }

  public static dateStringToNgbDate(date: String) {
    const d: String[] = date.split('/');
    return new NgbDate(Number(d[2]), Number(d[1]), Number(d[0]));
  }

  public static buildEndpoint(endpoint: string, pathParam: object): string {
    let regex: RegExp;
    for (let key in pathParam) {
      regex = new RegExp(':'+key);
      endpoint = endpoint.replace(regex, pathParam[key]);
    }
    return endpoint;
  }
}
