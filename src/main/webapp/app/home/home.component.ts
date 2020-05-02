import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { Router /* , ActivatedRoute, ParamMap */ } from '@angular/router';

import { ISearchResult } from 'app/shared/search/search-result.model';
import { SearchService } from 'app/shared/search/search.service';

import { IPicture } from 'app/shared/model/picture.model';
import { PictureSearchService } from 'app/shared/search/picture-search.service';

@Component( {
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.scss']
} )
export class HomeComponent implements OnInit, OnDestroy {
    account: Account | null = null;
    authSubscription?: Subscription;

    searchText?: string;
    searchResult?: ISearchResult;
    
    randomPicture?: IPicture;
    
    constructor( private accountService: AccountService, private loginModalService: LoginModalService, private router: Router,
            private searchService: SearchService, private pictureSearchService: PictureSearchService ) { }

    ngOnInit(): void {
        this.authSubscription = this.accountService.getAuthenticationState().subscribe( account => ( this.account = account ) );

        // If a search had earlier been executed, show it again:
        if ( this.searchService.getLatestSearchText() !== '' ) {
            this.searchText = this.searchService.getLatestSearchText();
            this.search();
        }
        
        // Get a random picture for the front page:
        this.pictureSearchService.getRandomPicture().subscribe(( res: HttpResponse<IPicture> ) => ( this.randomPicture = res.body || {} ));
    }

    isAuthenticated(): boolean {
        return this.accountService.isAuthenticated();
    }

    login(): void {
        this.loginModalService.open();
    }

    ngOnDestroy(): void {
        if ( this.authSubscription ) {
            this.authSubscription.unsubscribe();
        }
    }

    search(): void {
        this.searchService.search( this.searchText || '' ).subscribe(
            ( res: HttpResponse<ISearchResult> ) => this.onGetSearchResult( res.body || {} ) );
    }

    private onGetSearchResult( result: ISearchResult ): void {
        this.searchResult = result;

        // Exception, if the result contains 1 specimen, navigate to it:
        if ( result.specimen ?.length === 1) {
            this.searchService.resetLatestSearch();
            this.router.navigateByUrl( '/specimen/' + result.specimen[0].id + '/view' );
        }
    }
}
