import { TestBed } from '@angular/core/testing';

import { UseranswerService } from './useranswer.service';

describe('UseranswerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UseranswerService = TestBed.get(UseranswerService);
    expect(service).toBeTruthy();
  });
});
