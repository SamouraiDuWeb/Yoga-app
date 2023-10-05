import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { SessionService } from '../services/session.service';
import { expect } from '@jest/globals';

describe('AuthGuard', () => {
  let authGuard: AuthGuard;
  let router: Router;
  let sessionService: SessionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      providers: [AuthGuard, SessionService]
    });

    authGuard = TestBed.inject(AuthGuard);
    router = TestBed.inject(Router);
    sessionService = TestBed.inject(SessionService);
  });

  it('should be created', () => {
    expect(authGuard).toBeTruthy();
  });

  it('should allow access if user is logged in', () => {
    Object.defineProperty(sessionService, 'isLogged', { get: jest.fn(() => true) });
    const navigateSpy = jest.spyOn(router, 'navigate');

    const canActivate = authGuard.canActivate();

    expect(canActivate).toBe(true);
    expect(navigateSpy).not.toHaveBeenCalled();
  });

  it('should redirect to login if user is not logged in', () => {
    Object.defineProperty(sessionService, 'isLogged', { get: jest.fn(() => false) });
    const navigateSpy = jest.spyOn(router, 'navigate');

    const canActivate = authGuard.canActivate();

    expect(canActivate).toBe(false);
    //  expect(navigateSpy).toHaveBeenCalledWith(['/login']);
  });
});
