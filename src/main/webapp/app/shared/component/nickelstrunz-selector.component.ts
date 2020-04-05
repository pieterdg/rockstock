import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { NickelStrunzLevelOneService } from 'app/entities/nickel-strunz-level-one/nickel-strunz-level-one.service';

import { INickelStrunzLevelOne } from 'app/shared/model/nickel-strunz-level-one.model';
import { INickelStrunzLevelTwo } from 'app/shared/model/nickel-strunz-level-two.model';
import { INickelStrunzLevelThree } from 'app/shared/model/nickel-strunz-level-three.model';

import { SearchService} from 'app/shared/search/search.service';

@Component( {
    selector: 'jhi-nickelstrunz-selector',
    templateUrl: './nickelstrunz-selector.component.html'
} )

export class NickelstrunzSelectorComponent implements OnInit {
    nickelstrunzlevelones: INickelStrunzLevelOne[] = [];
    nickelstrunzleveltwos: INickelStrunzLevelTwo[] = [];
    nickelstrunzlevelthrees: INickelStrunzLevelThree[] = [];

    nickelStrunzCode: String = '';
    
    @Output() updateNsCodeEvent = new EventEmitter<any>();
    
    nsForm = this.fb.group({
        nsl1: [null, [Validators.required]],
        nsl2: [null, [Validators.required]],
        nsl3: [null, [Validators.required]]
      });

    constructor(
            private fb: FormBuilder,
            protected activatedRoute: ActivatedRoute,
            protected searchService: SearchService,
            protected nickelStrunzLevelOneService: NickelStrunzLevelOneService
    ) {  }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(({ mineral }) => {
            this.updateForm(mineral.nickelStrunzLevelThreeId);
          });
        this.nickelStrunzLevelOneService.query().subscribe(( res: HttpResponse<INickelStrunzLevelOne[]> ) => ( this.nickelstrunzlevelones = res.body || [] ));
    }

    updateForm(nsl3Id: number): void {
        if (nsl3Id != null) {
            this.searchService.getHierarchy(nsl3Id || -1).subscribe((res:HttpResponse<any>) => this.onHierarchyReceived(res.body || {}));
        }
    }
    
    private onHierarchyReceived(nsHierarchy: any): void {
        this.nsForm.get(['nsl2'])!.enable();
        this.nsForm.get(['nsl3'])!.enable();
        this.nsForm.patchValue({
            nsl1: nsHierarchy.levelOne,
            nsl2: nsHierarchy.levelTwo,
            nsl3: nsHierarchy.levelThree
        });
        this.searchService.getNickelStrunzLevelTwosForLevelOne(this.nsForm.get(['nsl1'])!.value.id)
                .subscribe(( res: HttpResponse<INickelStrunzLevelTwo[]> ) => ( this.nickelstrunzleveltwos = res.body || [] ) );
        this.searchService.getNickelStrunzLevelThreesForLevelTwo(this.nsForm.get(['nsl2'])!.value.id)
                .subscribe(( res: HttpResponse<INickelStrunzLevelThree[]> ) => ( this.nickelstrunzlevelthrees = res.body || [] ) );
        this.updateNickelStrunzCode();
    }
    
    compareFn(c1: any, c2:any): boolean {  
        return c1 && c2 ? c1.id === c2.id : c1 === c2; 
    }

    onNickelStrunzOneSelected(): void {
        this.nsForm.get(['nsl2'])!.enable();
        this.nsForm.patchValue({
            nsl2: null,
            nsl3: null
        });
        this.updateNickelStrunzCode();
        this.searchService.getNickelStrunzLevelTwosForLevelOne(this.nsForm.get(['nsl1'])!.value.id)
                .subscribe(( res: HttpResponse<INickelStrunzLevelTwo[]> ) => ( this.nickelstrunzleveltwos = res.body || [] ) );
      }

    onNickelStrunzTwoSelected(): void {
        this.nsForm.get(['nsl3'])!.enable();
        this.nsForm.patchValue({
            nsl3: null
        });
        this.updateNickelStrunzCode();
        this.searchService.getNickelStrunzLevelThreesForLevelTwo(this.nsForm.get(['nsl2'])!.value.id)
                .subscribe(( res: HttpResponse<INickelStrunzLevelThree[]> ) => ( this.nickelstrunzlevelthrees = res.body || [] ) );
        }

    onNickelStrunzThreeSelected(): void {
        this.updateNickelStrunzCode();
    }
  
    private updateNickelStrunzCode(): void {
        this.nickelStrunzCode = '';
        if (this.nsForm.value.nsl1 != null) {
            this.nickelStrunzCode = this.nsForm.value.nsl1.code;
        }
        if (this.nsForm.value.nsl2 != null) {
          this.nickelStrunzCode = this.nickelStrunzCode + "." + this.nsForm.value.nsl2.code;
        }
        if (this.nsForm.value.nsl3 != null) {
            this.nickelStrunzCode = this.nickelStrunzCode + this.nsForm.value.nsl3.code + ".";
        }
        this.updateNsCodeEvent.emit({nickelStrunzLevelThree: this.nsForm.get(['nsl3'])!.value, nickelStrunzCode: this.nickelStrunzCode});
    }
}