import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SummaryBonificoComponent } from './summary-bonifico.component';

describe('SummaryBonificoComponent', () => {
  let component: SummaryBonificoComponent;
  let fixture: ComponentFixture<SummaryBonificoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SummaryBonificoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SummaryBonificoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
