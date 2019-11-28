import { TestBed } from '@angular/core/testing';

import { BonificoService } from './bonifico.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('BonificoService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule
    ]
  }));

  it('should be created', () => {
    const service: BonificoService = TestBed.get(BonificoService);
    expect(service).toBeTruthy();
  });
});
