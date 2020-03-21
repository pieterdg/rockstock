import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
  searchText: String = "";

  constructor(private accountService: AccountService, private loginModalService: LoginModalService) {}

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }
  
  search(): void {
      //        this.searchService.search( this.searchText ).subscribe(
      //            ( res: HttpResponse<ISearchResult[]> ) => this.onGetSearchResults( res.body ), ( res: HttpErrorResponse ) => this.onGetSearchError() );
  }

  //    protected onGetSearchResults( results: ISearchResult[] ) {
  //        this.searchResults = results;
  //    }
  //
  //    protected onGetSearchError() {
  //    }
  }
