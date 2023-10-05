import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { UnauthGuard } from './unauth.guard';
import { SessionService } from '../services/session.service';
import { expect } from '@jest/globals';

describe('UnauthGuard', () => {
  let unauthGuard: UnauthGuard;
  let router: Router;
  let sessionService: SessionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      providers: [UnauthGuard, SessionService]
    });

    unauthGuard = TestBed.inject(UnauthGuard);
    router = TestBed.inject(Router);
    sessionService = TestBed.inject(SessionService);
  });

  it('should be created', () => {
    expect(unauthGuard).toBeTruthy();
  });

  it('should allow access if user is not logged in', () => {
    Object.defineProperty(sessionService, 'isLogged', { get: jest.fn(() => false) });
    const navigateSpy = jest.spyOn(router, 'navigate');

    const canActivate = unauthGuard.canActivate();

    expect(canActivate).toBe(true);
    expect(navigateSpy).not.toHaveBeenCalled();
  });

  it('should redirect to rentals if user is logged in', () => {
    Object.defineProperty(sessionService, 'isLogged', { get: jest.fn(() => true) });
    const navigateSpy = jest.spyOn(router, 'navigate');

    const canActivate = unauthGuard.canActivate();

    expect(canActivate).toBe(false);
  });
});
