import { Pipe, PipeTransform } from '@angular/core';
import {NgbDate} from '@ng-bootstrap/ng-bootstrap';
import {Utils} from '../utils';

@Pipe({ name: 'customDateString' })
export class NgbDateToStringFormatPipe implements PipeTransform {
  transform(date: NgbDate): string {
    return Utils.ngbDateToString(date);
  }
}
