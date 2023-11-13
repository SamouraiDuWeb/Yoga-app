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

  it('Created', () => {
    expect(authGuard).toBeTruthy();
  });

  it('Grants access when user is logged in', () => {
    sessionService.isLogged = true;
    const navigateMock = jest.spyOn(router, 'navigate');

    expect(authGuard.canActivate()).toBe(true);
    expect(navigateMock).not.toHaveBeenCalled();
  });

  it('Redirects to login when user isn\'t logged in', () => {
    sessionService.isLogged = false;
    const navigateMock = jest.spyOn(router, 'navigate');

    expect(authGuard.canActivate()).toBe(false);
  });
});
