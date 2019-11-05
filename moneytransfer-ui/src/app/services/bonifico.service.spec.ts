import { TestBed } from '@angular/core/testing';

import { BonificoService } from './bonifico.service';

describe('BonificoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BonificoService = TestBed.get(BonificoService);
    expect(service).toBeTruthy();
  });
});
