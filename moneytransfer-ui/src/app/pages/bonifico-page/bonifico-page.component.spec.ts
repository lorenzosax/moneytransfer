import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BonificoPageComponent } from './bonifico-page.component';

describe('BonificoPageComponent', () => {
  let component: BonificoPageComponent;
  let fixture: ComponentFixture<BonificoPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BonificoPageComponent ]
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
