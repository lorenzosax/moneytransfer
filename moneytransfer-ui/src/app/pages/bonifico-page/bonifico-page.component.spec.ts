import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsModule } from '@angular/forms';
import { NgbDateParserFormatter, NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgxSpinnerModule } from 'ngx-spinner';
import { BonificoPageComponent } from './bonifico-page.component';
import { SummaryBonificoComponent } from './summary-bonifico/summary-bonifico.component';
import { LoadingOverlayComponent } from '../../components/loading-overlay/loading-overlay.component';
import { NgbDateCustomParserFormatter } from '../../utils/filter/dateformat';
import { NgbDateToStringFormatPipe } from '../../utils/pipe/ngb-date-to-string-format.pipe';

describe('BonificoPageComponent', () => {
  let component: BonificoPageComponent;
  let fixture: ComponentFixture<BonificoPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        FormsModule,
        NgbDatepickerModule,
        FontAwesomeModule,
        NgxSpinnerModule,
        HttpClientTestingModule
      ],
      declarations: [
        BonificoPageComponent,
        SummaryBonificoComponent,
        LoadingOverlayComponent,
        NgbDateToStringFormatPipe
      ],
      providers: [
        {provide: NgbDateParserFormatter, useClass: NgbDateCustomParserFormatter}
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BonificoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
